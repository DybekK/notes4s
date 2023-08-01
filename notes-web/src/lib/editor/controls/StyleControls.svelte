<script lang="ts">
    import {Button} from 'flowbite-svelte'
    import {LetterBoldOutline, LetterItalicOutline, LetterUnderlineOutline} from 'flowbite-svelte-icons'
    import {editorStore} from "$lib/editor.store";

    const {store: editor} = editorStore

    $: icons = [
        {
            icon: LetterBoldOutline,
            isActive: () => $editor?.isActive('bold'),
            handler: () => $editor.chain().focus().toggleBold().run()
        },
        {
            icon: LetterItalicOutline,
            isActive: () => $editor?.isActive('italic'),
            handler: () => $editor.chain().focus().toggleItalic().run()
        },
        {
            icon: LetterUnderlineOutline,
            isActive: () => $editor?.isActive('underline'),
            handler: () => $editor.chain().focus().toggleUnderline().run()
        } //todo: do underline in css
    ]
</script>

{#each icons as {icon, isActive, handler}}
    <Button on:click={handler} color="light" outline={isActive()} size="xs">
        <svelte:component this={icon} size="xs"/>
    </Button>
{/each}
