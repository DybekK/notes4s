<script lang="ts">
    import {Navbar, NavBrand, Input, DarkMode} from 'flowbite-svelte'
    import {SearchOutline} from 'flowbite-svelte-icons'
    import Controls from "$lib/editor/controls/Controls.svelte";
</script>

<Navbar class="z-20 border-b top-0 left-0 right-0 backdrop-filter backdrop-blur-3xl sticky bg-transparent dark:bg-transparent">
    <NavBrand class="z-10" href="/">
        <span class="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
            Notes4s ✍️
        </span>
    </NavBrand>
    <div class="z-10 flex w-44 justify-between">
        <Controls/>
    </div>
    <div class="z-10 flex items-center">
        <div class="hidden relative md:block">
            <div class="flex absolute inset-y-0 left-0 items-center pl-3 pointer-events-none">
                <SearchOutline size="sm" strokeWidth="1"/>
            </div>
            <Input id="search-navbar" size="sm" class="pl-10" placeholder="Search..."/>
        </div>
        <DarkMode class="ml-5"/>
    </div>
</Navbar>
