<script lang="ts">
    import Note from '$lib/layout/sidebar/Note.svelte'
    import {SidebarGroup} from "flowbite-svelte";
    import {notesStore} from "$lib/notes.store";
    import {editorStore} from "$lib/editor.store";

    const {notesState} = notesStore
    const {setEditorContext} = editorStore

    let {notes, activeNoteId} = $notesState

    function handleNoteChange(id: number) {
        const note = notes.find(note => note.id === id)
        activeNoteId = id
        setEditorContext(note?.content)
    }
</script>

<SidebarGroup class="flex flex-col">
    {#each notes as {id, title} (id)}
        <Note on:click={() => handleNoteChange(id)} active={id === activeNoteId} {title}/>
    {/each}
</SidebarGroup>